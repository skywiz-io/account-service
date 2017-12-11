pipeline { 
      stages {
          agent {
              docker {
                  image 'maven:3-alpine'
                  args '-v $HOME/.m2:/root/.m2 -v /tmp:/app'
              }
          }
          stage('Maven Build') {
              steps {
                  sh 'mvn clean install -DskipTests'
                  sh 'cp target/account-service-1.0.0.jar /app'
              } 
          }
      }
    stages {
          agent any
          }
          stage('Build') {
              steps {
                  sh 'mkdir target'
                  sh 'cp /tmp/account-service-1.0.0.jar target/account-service-1.0.0.jar'
                  sh 'docker build -t account-service .'
              } 
          }
      }
    
          
}
