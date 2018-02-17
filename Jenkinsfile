pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git "https://github.com/adrianopo-ciandt/Cache"
                sh "git branch --set-upstream-to=origin/${env.BRANCH_NAME}"
                sh "git pull"

                echo "My branch is: ${env.BRANCH_NAME}"
            }
        }
        stage('Build Dev') {
            steps {
                echo "Start - Build Dev"
                sh "./gradlew clean"
                sh "./gradlew generateDevKeys"
                sh "./gradlew assembleDevRelease"
                archive(includes: '**/*.apk')
                echo "End - Build Dev"
            }
        }
    }
}