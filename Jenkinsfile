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
                sh "docker login -u itamar -p Aa123123"
		        sh "docker push itamar/${App_Name}:${BUILD_NUMBER}"
            }
        }
	    stage('Deploy and Test') {
            agent any 
            steps {
                sh "oc login https://35.226.193.77:8443/ -u developer -p developer --insecure-skip-tls-verify=true"
                sh "oc new-app itamar/${App_Name}:${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER} -e ${Parameters}"
		        sh "oc expose service ${App_Name}-v${BUILD_NUMBER} --name ${App_Name}-v${BUILD_NUMBER}"
		        sh "oc scale dc ${App_Name}-v${BUILD_NUMBER} --replicas=2"
                if (sh ".tests/test.sh"){
                    echo "Test passed"
                }
                else{
                    echo "Test Failed"
                }
            }                
        }
    }
}

