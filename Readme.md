# WanAndroid 

## 主要涉及的技术

RecyclerView，RecyclerView联动滑动，viewpager，okhttp3，gson，banner，glide，litepal，MVP架构的使用。

## 不足之处

###  cookie的持久化存储未实现

​	cookie相关的知识不够理解，返回的set-cookie有好几条，不知道要储存哪几条，储存的格式，还有收藏相关的接口不知道要带上哪些cookie才能成功。cookie是否失效的判断不会。目前的登录只是存储了返回的相关的基本信息。

###  首页和问答页面没有做刷新和加载更多功能

###  banner没有加到下方的RecyclerView的头部

### ui界面不算美观

##  代码介绍

​	这个app主要分为 `主页` `问答` `Todo` `体系` `我的` `登录` `注册` 等页面；

### HttpUtil类

​         这个类是使用的okhttp，封装了get和post两种网络请求方法。

### 主页面

​	主要是        `HomeFragment`  `HomePresenter`  `HomeModel` 这三个类。

​        主页面上方是一个banner，使用的第三方库，下方是一个RecyclerView，用于显示文章列表。

​	` ArticleData` 是首页文章列表的数据类。

​	`BannerData` 是首页banner的数据类。

<img src="https://pic.imgdb.cn/item/613b9d7244eaada739c13a89.jpg" width = "108" height = "230" alt="图片名称" />

### 问答界面

​	问答界面就是一个瀑布流样式的RecyclerView

<img src="https://pic.imgdb.cn/item/613ba07944eaada739c53677.jpg" width = "108" height = "230">

### 体系界面

​	体系界面是采用的左右两个RecyclerView进行联动，但是右边的RecyclerView的点击事件还没有做。

​	左边就是一个常规的RecyclerView。右边是一个RecyclerView内部嵌套了一个RecyclerView。

​	监听左边的RecyclerView点击获取到点击的position，然后使右边的外层的RecyclerView滚动到相同的position；

​	监听右边的RecyclerView的滚动，获取滚动时的第一条展示的position，获取到这条position的id值，查询左侧和这个id相同的那一条的位置，滚动到相应的位置。

```java
systemLeftAdapter.setLeftCheckListener(new SystemLeftAdapter.LeftCheckListener() {
        @Override
        public void onItemClick(int position) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)rightRecyclerView.getLayoutManager();
            linearLayoutManager.scrollToPositionWithOffset(position,0);
        }
    });
    rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //获取滚动时的第一条展示的position
            LinearLayoutManager layoutManager2 = (LinearLayoutManager)rightRecyclerView.getLayoutManager();
            int firstVisibleItemPosition = layoutManager2.findFirstVisibleItemPosition();
            int outId = dataBeanList.get(firstVisibleItemPosition).getId();
            int pos=0;
            for (int i = 0; i <dataBeanList.size() ; i++) {
                int id = dataBeanList.get(i).getId();
                if (id==outId){
                    pos=i;
                }
            }
            LinearLayoutManager layoutManager3 = (LinearLayoutManager)leftRecyclerView.getLayoutManager();
            layoutManager3.scrollToPositionWithOffset(pos,0);
        }
    });
}
```

<img src="https://pic.imgdb.cn/item/613ba0e944eaada739c5bd4d.gif" width = "108" height = "230">

### 我的界面

​	由于cookie不太会使用，所以使用litepal存储的登录成功返回的信息，

​	在   ` MainActivity`中首先初始化litepal数据库，如果数据库为空，默认创建一条数据。

```java
LitePal.initialize(this);
List<UserInformation> list = LitePal.findAll(UserInformation.class);
if (list.size()==0){
    UserInformation userInformation = new UserInformation();
    userInformation.setNickname("去登陆");
    userInformation.setCoinCount(" ");
    userInformation.save();
}
```

​	在   `MyFragment` 中点击头像的图标。判断数据库中有几条数据，如果只有一条就代表没有登录，跳转到登录和注册的页面。

```
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (list.size() == 1) {
            Intent intent = new Intent(view.getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
});
```

​	登录或注册成功，储存返回的信息到数据库第二条。

​	点击退出登录按钮，删除当前用户的信息，这样会存在像积分没有办法及时同步的问题，也不算是登录持久化。
<img src="https://pic.imgdb.cn/item/613ba12c44eaada739c60f4d.jpg"  width = "108" height = "230" >

### 登录和注册页面

​	登录和注册页面就是两个fragment

​        登录成功之后这里没有使用gosn，手动解析的josn，比gson感觉要方便一些这里。

<img src="https://pic.imgdb.cn/item/613b9fd944eaada739c46c60.gif" width = "108px" height = "230px" >

## 总结

​	这次做的这个项目我自己感觉是很垃圾的，有特别多的不足的地方。不过相比于最开始我自己觉得还是有很多的进步的。

