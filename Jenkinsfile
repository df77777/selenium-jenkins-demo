pipeline {
    agent any

    triggers {
       cron @daily
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the code from SCM...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                echo 'Archiving test reports...'
                archiveArtifacts artifacts: 'target/surefire-reports/**/*.xml', allowEmptyArchive: true
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Generating code coverage report...'
                jacoco execPattern: '**/target/jacoco.exec'
            }
        }
    }

    post {
        always {
            echo 'Processing post-build actions...'
            // Interprétation des tests JUnit pour Jenkins
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Build successful!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
