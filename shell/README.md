使用 `systemd` 注册服务，可以配置使得进程在崩溃宕机的时候自动重启

在大多数 Linux 发行版中，`systemd` 服务单元文件通常存储在 `/etc/systemd/system` 目录中。需要将 `.service` 后缀的服务单元文件放在该目录下，并确保其权限为 `644`.

一旦将服务单元文件放在正确的位置，就可以使用 `systemctl` 命令启用和管理服务。例如，要启用名称为 "management.service" 的服务，请运行以下命令：

```
sudo systemctl enable management.service
```

然后，可以使用以下命令来检查服务状态：

```
systemctl status management.service
```

要启动或停止服务，请使用以下命令：

```
sudo systemctl start management.service
sudo systemctl stop management.service
```

注意，如果对 `systemd` 进行任何更改（如创建、编辑或删除服务），都需要运行以下命令重新加载 `systemd` 配置：

```
sudo systemctl daemon-reload
```

这会告诉 `systemd` 重新加载配置文件并更新内部配置数据库。