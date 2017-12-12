pipeline { 
    agent none
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
                sh "docker login -u itamar -p Aa123123"
		        sh "docker push itamar/${App_Name}:${BUILD_NUMBER}"
            }
        }
	stage('Deploy and Test') {
            agent any 
            steps {
                sh "oc login https://35.226.193.77:8443/ -u developer -p developer --insecure-skip-tls-verify=true"
		sh "oc project dev"
		sh "oc new-app itamar/${App_Name}:${BUILD_NUMBER} --name ${App_Name}-v${version}"
		sh "oc expose service ${App_Name}-v${version} --name ${App_Name}-v${version}"
		sh "oc scale dc ${App_Name}-v${version} --replicas=${replicas}"
            }
        }
    }
}
