mkdir -p ./data

mongod --port 27017 --dbpath ./data --logappend
