pipeline {
    agent {
        docker {
            image 'evarga/jenkins-slave'
            args '-v /var/run/docker.sock:/var/run/docker.sock --privileged -it'
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
