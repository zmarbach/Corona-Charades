//Jenkins build pipeline

    pipeline {
        environment {
            PROD_URL  = "https://www.google.com" // replace with correct url, maybe even set variable in K8S block
        }
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
                }
            }

            stage('Deploy app to K8S Cluster') {
                steps {
                    timeout(time: 15, unit: 'MINUTES') {
                        input(message: "Should we deploy app to AWS?")
                    }
                    echo '***** Deploying app to K8S cluster on AWS*****'
                }
                post {
                    success {
                        echo "Deployment successful. App is now running on K8S cluster in AWS"
                        echo "Go here to see the site live in production ---> $PROD_URL"
                    }
                }
            }
        }
    }
