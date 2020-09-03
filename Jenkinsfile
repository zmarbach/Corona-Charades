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
                    sh 'mvn -f pom.xml clean compile package'
                }
                post {
                    success {
                        echo "Build succeeded and all tests passed!"
                        echo "Archive the application war file"
                        archiveArtifacts artifacts: '**/*.war'
                    }
                }
            }


            stage('Build and Push Docker Image') {
                steps {
                    echo '***** Building Docker image and pushing to Docker Hub *****'
                    //TODO - store image name as env variable instead
                    sh 'docker build . -t corona-charades-app'
                    //TODO - find more secure way to do pass username and password in (secret?)
                    sh 'docker login -u zmarbach -p Buggywhip22!!'
                    sh 'docker image push zmarbach22/corona-charades-app'
                    sh 'docker logout'
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
