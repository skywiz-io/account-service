pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'docker images'
                sh 'mvn install dockerfile:build -DskipTests'
                sh 'docker images'
            }
        }
    }
}
