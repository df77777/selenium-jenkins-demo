pipeline {
    agent any

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
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }
        
        stage('Generate HTML Report') {
            steps {
                echo 'Generating Surefire HTML report...'
                bat 'mvn surefire-report:report-only'
            }
        }

        stage('Archive Reports') {
            steps {
                echo 'Archiving XML and HTML reports...'
                archiveArtifacts artifacts: 'target/surefire-reports/**/*.xml', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/site/report/surefire.html', allowEmptyArchive: true
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
