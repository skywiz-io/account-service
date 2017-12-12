def oc_deploy (String project){
    sh "oc login https://35.226.193.77:8443/ -u developer -p developer --insecure-skip-tls-verify=true"
    sh "oc project ${project}"
    sh "oc new-app itamar/${App_Name}:${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER} -e ${Parameters}"
	sh "oc expose service ${App_Name}-v${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER}"
	sh "oc scale dc ${App_Name}-v${BUILD_NUMBER} --replicas=2"
}
pipeline { 
    agent none
    environment {
        App_Name    = 'account-service'
        Parameters  = "DB_URL='jdbc:mysql://devops-accounts-mysql:3306/accounts?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true' -e DB_USER='user' -e DB_PASSWORD='password'"
    }
    stages {
        stage('Maven Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2 -v /tmp:/app'
                }
            }
            steps {
                sh 'mvn clean install -DskipTests'
                sh 'cp target/account-service-1.0.0.jar /app'
            } 
        }
        stage('Build Docker image') {
            agent any 
            steps {
                sh 'cp /tmp/account-service-1.0.0.jar target/account-service-1.0.0.jar'
                sh "docker build -t itamar/${App_Name}:${BUILD_NUMBER} ."
		        sh "docker push itamar/${App_Name}:${BUILD_NUMBER}"
            }
        }
	    stage('Deploy on openshift And Test') {
            agent any 
            steps {
                oc_deploy("dev")
                sh "sh .tests/*.sh"
            }    
        }
    }
    post {
        always {
            // Shutdown the environment
            echo "Shutting-down the env"
            //sh "for item in `oc get all | grep ${App_Name}-v${BUILD_NUMBER} |grep -v rc| awk '{print $1}'`; do oc delete $item; done"
        }
        success {
            // Send Success mail message And Depoly the same version on Test project for manual QA
            echo "Success"
            sh "oc login https://35.226.193.77:8443/ -u developer -p developer --insecure-skip-tls-verify=true"
            sh "oc project test"
            sh "oc new-app itamar/${App_Name}:${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER} -e ${Parameters}"
	        sh "oc expose service ${App_Name}-v${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER}"
	        sh "oc scale dc ${App_Name}-v${BUILD_NUMBER} --replicas=2"
        }
        failure {
            //Remove Image from repo and Send Failure message
            echo "Failure"
      }
    }
}

