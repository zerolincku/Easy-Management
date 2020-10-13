pwd
ls /
ssh -p 22 -Tq root@119.3.159.234 << remotessh
cd /home/lin
date >> log
exit
remotessh
# scp -P 22 target/management-0.0.1.jar root@119.3.159.234:/home/lin
ssh -p 22 root@119.3.159.234 "echo SUCCESS"