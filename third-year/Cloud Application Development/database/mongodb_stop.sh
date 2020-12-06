mongo --port 27017 <<EOF
db.adminCommand( { shutdown: 1 } )
EOF

ps aux | grep -v grep | grep mongod