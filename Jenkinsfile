pipeline {
    agent any
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

    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}
