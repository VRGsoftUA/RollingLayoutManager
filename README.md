# RollingLayoutManager

<img src="https://github.com/VRGsoftUA/RollingLayoutManager/blob/master/image.gif" alt="alt text" style="width:200;height:200">

#### [HIRE US](http://vrgsoft.net/)

# Usage

*For a working implementation, Have a look at the Sample Project - app*

1. Include the library as local library project.
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.VRGsoftUA:RollingLayoutManager:1.0'
}
```
2. Create an insance of RollingLayoutManager
```java
  RecyclerView recyclerView = findViewById(R.id.recycler_view);
  RollingLayoutManager rollingLayoutManager = new RollingLayoutManager(this);
  recyclerView.setLayoutManager(rollingLayoutManager);
```
     #### Contributing
* Contributions are always welcome
* If you want a feature and can code, feel free to fork and add the change yourself and make a pull request
