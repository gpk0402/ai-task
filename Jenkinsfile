pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SECRET_TOKEN')
         TOMCAT_CREDENTIALS = credentials('TOMCAT_CREDENTIALS') // Use the ID of the Jenkins credential containing Tomcat credentials

    }

    stages {
        stage('Build, Test, and Generate Coverage') {
            steps {
                bat "mvn clean verify"
                bat "mvn jacoco:prepare-agent"
            }
        }
    stage('SonarQube Analysis') {
            steps {
                bat "mvn sonar:sonar -Dsonar.projectKey=jenkins-pipeline -Dsonar.projectName='jenkins-pipeline' -Dsonar.host.url=http://localhost:9000 -Dsonar.login=%SONAR_TOKEN% -Dsonar.java.coveragePlugin=jacoco"
            }
            post{
            success{
            archiveArtifacts artifacts:'**/target/*.war'
            }
            }

        }

        stage('Deploy to Tomcat') {
             steps {
                           // Deploy the war file to Tomcat
                   deploy adapters: [tomcat9(credentialsId: 'TOMCAT_CREDENTIALS', path: '', url: 'http://localhost:9095')], contextPath: 'assessment-jenkins-task', war: '**/*.war'
             }
             }
    }
}