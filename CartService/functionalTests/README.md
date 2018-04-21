docker build -t func-test .  

docker run -v `pwd`/test.js:/test/test.js --link scaffold-app:app func-test
