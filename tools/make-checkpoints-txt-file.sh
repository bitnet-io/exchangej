
echo '
# How to add a new libdohj checkpoints .txt file (radiocoinj modification)

### Update checkpoint
1. `git checkout https://github.com/c4pt000/libdohj` 
2. `cd tools ; ./build-checkpoints --peer=127.0.0.1 --days=3` (you have to have a local Radiocoin node running)
3. Copy generated `/tools/checkpoints.txt` into `core/src/main/resources/wallet` or where its needed
┌─[c4pt@fedora]─[/home/c4pt/opt/libdohj/tools]
└──╼ #
'
echo '

building checkpoints txt file for 3 days using local radiocoin-qt or daemon wallet

./build-checkpoints --peer=127.0.0.1 --days=3
'
./build-checkpoints --peer=127.0.0.1 --days=3 

