//Jenkins build pipeline

    pipeline {
        agent any
        stages {
            stage('Build and Test Charades App') {
                steps {
                    echo '***** Building charades app and running all tests.... *****'
                    bat 'mvn -f pom.xml clean compile package'
                    //do steps to build war file
                }
                post {
                    success {
                        echo "Build succeeded and all tests passed!"
                    }
                    // failure {
                        //do something to send email notifying about failure
                    // }
                }
            }


            stage('Build and Push Docker Image') {
                steps {
                    echo '***** Building Docker image and pushing to DCR *****'
                }
                post {
                    success {
                        echo "Docker image successfully built and pushed!"
                    }
                    // failure {
                        //do something to send email notifying about failure
                    // }
                }
            }

            stage('Deploy app to K8S Cluster') {
                steps {
                    echo '***** Deploying app to K8S cluster on AWS*****'
                }
                post {
                    success {
                        echo "Deployment successful. App is now running on K8S cluster in AWS"
                        echo "Go here to see the site live in production ---> "// use env variable to concat url here
                    }
                }
            }
        }
    }
