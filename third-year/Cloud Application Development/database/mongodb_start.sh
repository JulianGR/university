mkdir -p ./data

mongod --port 27017 --dbpath ./data --logpath /usr/local/var/log/mongodb/mongo.log --logappend --fork

ps aux | grep -v grep | grep mongod