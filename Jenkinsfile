pipeline { 
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
        stage('Build') {
            agent any 
            steps {
                sh 'mkdir target'
                sh 'cp /tmp/account-service-1.0.0.jar target/account-service-1.0.0.jar'
                sh 'docker build -t account-service .'
            }
        }
    }
}
