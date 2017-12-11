pipeline {
    agent {
        docker {
            image 'docker:stable-dind'
            args '--privileged'
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
