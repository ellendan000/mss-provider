pipeline {
    agent { 
        docker {
            image 'gradle:5.3.0-jdk8' 
            args '-m 300M --memory-swap=1g'
        }
    }
    stages {
        stage('build') {
            steps {
                sh 'gradle clean build --info'
            }
        }
    }
}
