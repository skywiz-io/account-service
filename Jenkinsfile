pipeline {
    agent {
        docker {
            image 'twalter/maven-docker'
            args '-v /var/run/docker.sock:/var/run/docker.sock --privileged'
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
