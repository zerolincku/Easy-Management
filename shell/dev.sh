pwd
ls /
ssh -p 22 -Tq root@119.3.159.234 << remotessh
cd /home/lin
date >> log
rm -f README.md
exit
remotessh
scp -P 22 README.md root@119.3.159.234:/home/lin
echo SUCCESS!!