pipeline {
    agent any

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Choisissez le navigateur sur lequel exécuter les tests'
        )
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
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests on ${params.BROWSER}"
                bat "mvn test -DBROWSER=${params.BROWSER}"
            }
        }

        stage('Generate HTML Report') {
            steps {
                echo 'Generating Surefire HTML report...'
                bat 'mvn surefire-report:report-only -DoutputDirectory=target/reports'
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/reports',
                    reportFiles: 'surefire.html',
                    reportName: 'Surefire HTML Report'
                ])
            }
        }

        stage('Archive Reports') {
            steps {
                echo 'Archiving XML and HTML reports...'
                archiveArtifacts artifacts: 'target/surefire-reports/**/*.xml', allowEmptyArchive: true,  fingerprint: true
                archiveArtifacts artifacts: 'target/reports/surefire.html', allowEmptyArchive: false,  fingerprint: true
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
