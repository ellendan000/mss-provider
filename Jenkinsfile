pipeline {
    agent {
        docker {
            image 'gradle:5.3.0-jdk8'
            args '-v /tmp/gradle-caches:/home/gradle/.gradle/caches'
        }
    }
    stages {
        stage('pmd') {
            steps {
                sh 'gradle pmdMain pmdTest -q'
            }
        }
        stage('test') {
            steps {
                sh 'gradle clean test'
            }
        }
        stage('build') {
            steps {
                sh 'gradle clean build --info'
            }
        }
    }

//    post {
//        always {
//            junit 'build/reports/**/*.xml'
//        }
//    }
}
