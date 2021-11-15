package com.linck.management;


import com.linck.management.system.mapper.SysPermissionMapper;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ManagementApplicationTests {

    volatile int successCount = 0;
    @Autowired
    SysPermissionMapper sysPermissionMapper;
    @Autowired
    SysUserService sysUserService;

    @Test
    void contextLoads() {
        List<SysPermission> sysPermissions = sysPermissionMapper.listByUserId(1L);
        sysPermissions.forEach(System.out::println);
    }

    @Test
    void addUserTest() {
        /*Random random = new Random();
        LoginUserDTO user = new LoginUserDTO();
        int successCount = 0;
        user.setVerificationCode("1");
        for (int i = 0; i < 10000; i++) {
            int length = random.nextInt(31) + 1;
            //System.out.print(length + " ");
            String account = UUID.randomUUID().toString().substring(0, length);
            length = random.nextInt(31) + 1;
            //System.out.print(length + " ");
            //System.out.println();
            String password = UUID.randomUUID().toString().substring(0, length);
            user.setAccount(account);
            user.setPwd(password);
            SysUser register = sysUserService.register(user);
            if (register != null) {
                successCount++;
            }
            System.out.println("成功插入用户数量:" + successCount);
        }*/

    }

    @Test
    void addUserTestThread() {

        /*ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 7; i++) {
            executor.submit(() -> {
                Random random = new Random();
                LoginUserDTO user = new LoginUserDTO();

                user.setVerificationCode("1");
                for (int j = 0; j < 10000; j++) {
                    int length = random.nextInt(31) + 1;
                    String account = UUID.randomUUID().toString().substring(0, length);
                    length = random.nextInt(31) + 1;
                    String password = UUID.randomUUID().toString().substring(0, length);
                    user.setAccount(account);
                    user.setPwd(password);
                    SysUser register = sysUserService.register(user);
                    if (register != null) {
                        successCount++;
                    }
                }
            });
        }
        Random random = new Random();
        LoginUserDTO user = new LoginUserDTO();

        user.setVerificationCode("1");
        for (int j = 0; j < 30000; j++) {
            int length = random.nextInt(31) + 1;
            String account = UUID.randomUUID().toString().substring(0, length);
            length = random.nextInt(31) + 1;
            String password = UUID.randomUUID().toString().substring(0, length);
            user.setAccount(account);
            user.setPwd(password);
            SysUser register = sysUserService.register(user);
            if (register != null) {
                successCount++;
            }
            System.out.println("成功插入用户数量:" + successCount);
        }*/
    }

}
