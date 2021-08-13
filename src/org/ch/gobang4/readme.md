# 五子棋4.0

### 这是个比较完善的版本，具体功能如下：

- 基本的棋盘、棋子绘制
 - 黑白双方轮流交替下棋
 - 启动界面，输入用户名密码登录
 - 存储棋路
- 移动洗盘窗口时进行重绘
- 判定输赢
- 悔棋
### 类的说明
- **StartUI**：启动界面，可以输入用户名密码用来登录，由于每次都要输入太麻烦，已注释掉用户验证。
- **GoBangUI：** 下棋界面，详见注释。重点关注**界面与监听器传参**的get\set方法 。                                          
- **Chess、BlackChess、WhiteChess：**一个父类，两个子类。
  - 重点关注**子类调用父类构造**的规则。
  - 其中 chessList[] 感觉是个重复且累赘的属性，目前用来实现重绘、判断重复落子、黑白子交替功能。其中前两个功能可以用bList[]、wList[] 代替，但是黑白子交替没有找到合适的替代方案。
- **GoBangConfig**：接口中的属性默认是final、static。所以用来储存棋子尺寸等常量，如果需要用这些常数时，实现接口即可。这是个很好的技巧。
- **StartGoBang**：main方法，用来启动项目。
- **GobangMouseListener**：游戏界面的鼠标点击监听器。实现了项目大部分主要功能。重点关注**落子行列号**的计算。
- **ButListener**：悔棋按钮的监听器。应关注**悔棋步骤**，详见注释。
- **StartListener**：启动界面的监听器。同样应关注get\set方法。
- **IsWin**：一个及其庞大的类，用来判断胜负。详见注释。

### 项目特点

​		没有使用较为简单的二维int数组来存储棋路。而是使用了更加符合面向对象的方法，新建了chess以及两个子类，通过使用**静态对象数组**来储存棋路。

​		尽可能的将类成员进行**封装**处理，尽可能合理的使用**static**关键字。static的正确使用可以极大的**方便传参**！！！不需要去写一堆get\set方法。

### 有待完善的地方

- 悔棋动作很多可以封装进Chess类，懒得改了。

- 判断输赢之后处理的不太好，初步设想是移除画笔，使其不可以继续落子，并且增加再来一盘按钮。
- 感觉 chessList[] 应该可以去掉、鼠标监听器中order、bOrder、wOrder三个属性可以进行封装。但是封装后怎么实现黑白子交替是个难题。
- 后期可以增加人机对战。

### swing中一些方法

```java
//排序的方法,来自java.unit包
Arrays.sort(arr); 

//添加背景图片的方法。icon-图标
private static final Image bgimg = new ImageIcon("img/ab.jpeg").getImage();
g.drawImage(bgimg, X, Y, (CLOUN - 1) * SIZE, (ROW - 1) * SIZE, null);

//窗体上放个标签，用作显示步数的文本框
JLabel BnumLab=new JLabel("白棋走了： 步");
BnumLab.setFont(new Font("", Font.BOLD, 16));  //设置前景，就是设置字体颜色大小啥的
BnumLab.setBounds(900,100,150,50);			//设置背景，就是设置文本框的大小、位置
this.add(BnumLab);

//通过界面上的按钮，获取界面对象
JButton btn = (JButton) e.getSource();
GobangUI gb= (GobangUI) btn.getRootPane().getParent();

//弹出消息框
JOptionPane.showMessageDialog(null,"黑棋赢");
```

![image-20210813131023699](C:\Users\ch_pc\AppData\Roaming\Typora\typora-user-images\image-20210813131023699.png)
