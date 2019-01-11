## 原理
每次获取accesstoken都会把之前的存在redis中的accesstoken删除掉，使得每次生成新的accesstoken时候，会把之前的的accesstoken删除，
得到的效果就是：只能让最新的accesstoken调用接口，过去的accesstoken不能调用接口
## 访问
访问呢：http://localhost:9000/getAccessToken?appId=14af213f1a1g56ad&&appSecret=afa231fa23gaga
获取到accessToken</br>
然后访问:http://localhost:9000/openApi/getUser?accessToken=1083768478162550784222，
再次访问：http://localhost:9000/getAccessToken?appId=14af213f1a1g56ad&&appSecret=afa231fa23gaga
获取到新的accessToken<b/>
此时用新的accessToken能让问接口，而之前生成的accessToken则不能再访问接口了