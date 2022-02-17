# prometheus-study
1.安装`Prometheus`(mac)
```shell
brew install prometheus
```
2.启动`Prometheus`
```shell
prometheus --config.file=/prometheus-study/src/main/resources/prometheus.yml
```
3.安装`Grafana`
```shell
brew install grafana
```
4.启动`Grafana`
```shell
brew services start grafana
```
5.登录`http://127.0.0.1:3000`，用户名/密码：`admin/admin`

6.在`Grafana`上新增`Prometheus`的数据源，配置的url为`http://127.0.0.1:9090`

7.创建查询面板，填写查询语句`prometheus_demo_gauge{application="prometheus-study"}`

8.保存查询面板，完工