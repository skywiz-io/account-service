def oc_deploy (String project){
    sh "oc login https://35.226.193.77:8443/ -u developer -p developer --insecure-skip-tls-verify=true"
    sh "oc project ${project}"
    sh "oc new-app itamar/${App_Name}:${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER} --env-file=param_file"
	sh "oc env dc/${App_Name}-v${BUILD_NUMBER} --from=secret/db-password"
    sh "oc expose service ${App_Name}-v${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER}"
	sh "oc scale dc ${App_Name}-v${BUILD_NUMBER} --replicas=2"
}
def oc_delete (String project)
{
    sh "oc project ${project}"
    sh "oc delete deploymentconfigs/${App_Name}-v${BUILD_NUMBER}"
    sh "oc delete routes/${App_Name}-v${BUILD_NUMBER}"
    sh "oc delete imagestreams/${App_Name}-v${BUILD_NUMBER}"
    sh "oc delete svc/${App_Name}-v${BUILD_NUMBER}"
}
pipeline { 
    agent any
    environment {
        App_Name    = 'account-service'
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
            oc_delete("dev")
        }
        success {
            // Send Success mail message And Depoly the same version on Test project for manual QA
            echo "Success"
            oc_deploy("test")
        }
        failure {
            //Remove Image from repo and Send Failure message
            echo "Failure"
      }
    }
}