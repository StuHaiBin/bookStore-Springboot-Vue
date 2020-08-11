package com.huang.store;

import com.huang.store.entity.book.*;
import com.huang.store.entity.dto.OrderDetailDto;
import com.huang.store.entity.dto.OrderDto;
import com.huang.store.entity.order.Expense;
import com.huang.store.entity.order.OrderDetail;
import com.huang.store.entity.order.OrderStatusEnum;
import com.huang.store.entity.user.Address;
import com.huang.store.entity.user.Cart;
import com.huang.store.entity.user.User;
import com.huang.store.mapper.BookMapper;
import com.huang.store.mapper.ExpenseMapper;
import com.huang.store.mapper.OrderMapper;
import com.huang.store.mapper.TopicMapper;
import com.huang.store.service.imp.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.sql.Timestamp;
import java.util.*;

@SpringBootTest
class StoreApplicationTests {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;

    @Autowired
    @Qualifier("firstUser")
    UserService userService;

    @Autowired
    @Qualifier("firstSort")
    SortService sortService;


    @Autowired
    @Qualifier("firstAddress")
    AddressService addressService;

    @Autowired
    @Qualifier("firstCart")
    CartService cartService;

    @Autowired
    @Qualifier("firstPublish")
    PublishService publishService;

    //测试添加图书
    @Test
    void contextLoads() {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        Book book = new Book();
        book.setBookName("平凡的世界");
        book.setAuthor("路遥");
        book.setPut(true);
        book.setBirthday(timeStamp);
        int result = bookService.addBook(book);
        System.out.println("result:"+result);
    }

    //测试删除图书
    @Test
    void deleteTest(){
        int result = bookService.deleteBook(1);
        System.out.println(result);
    }

    //测试查询所有图书
    @Test
    void getBooks(){
        System.out.println(bookService.getBook(1));
        List<Book> books=bookService.getBooks();
        System.out.println(books);
    }

    //测试按页查询图书
    @Test
    void getBooksByPage(){
        List<Book> books = bookService.getBooksByPage(0,5);
        Book book;
        for(int i=0;i<books.size();i++){
            book = books.get(i);
            System.out.println(book.toString());
            System.out.println("-------------------------");
        }
    }

    //测试修改图书
    @Test
    void modifyBook(){
        List<Book> bookList1 = bookService.getBookBySecond(121,1,2);
        System.out.println("bookRes1:"+bookList1.toString());
    }

    @Test
    void bookImgTest(){
        BookImg bookImg = new BookImg();
        bookImg.setIsbn("1");
        bookImg.setImgSrc("http://76212233");
        bookImg.setCover(false);
//        int result=bookService.deleteBookImgOfOne("HHH");
//        System.out.println("result"+result);
//        int result2=bookService.deleteOneBookImg(bookImg.getIsbn(),bookImg.getImgSrc());
//        System.out.println(result2);
//        List<String> list=bookService.getBookImgSrcList(bookImg.getIsbn());
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//            System.out.println("----------------------");
//        }
//        String img = bookService.getBookCover(bookImg.getIsbn());
//        System.out.println(img);
    }

    //添加用户
    @Test
    void TestUser(){
        User user = new User();
        user.setAccount("黄龙");
        user.setPassword("789");
        boolean flag = userService.isExist(user.getAccount(),"78910");
        System.out.println(flag);
    }

    @Test
    void TestSort(){
//        BookFirstSort bookFirstSort = new BookFirstSort();
//        bookFirstSort.setSortName("黄小龙");
//        bookFirstSort.setLevel("级别1");
//        bookFirstSort.setHasNext(true);
//        int result = sortService.addFirstSort(bookFirstSort);
//        System.out.println("result"+result);
//        sortService.modifyFirstSort(bookFirstSort);
//        sortService.deleteFirstSort(bookFirstSort.getSortName());
//        List<BookFirstSort> bookFirstSorts = sortService.getFirstSort();
//        for(int i=0;i<bookFirstSorts.size();i++){
//            System.out.println(bookFirstSorts.get(i).toString());
//            System.out.println("--------------------");
//        }

//        int result=sortService.deleteSecondSort(bookSecondSort.getUpperName(),bookSecondSort.getSortName());
//        System.out.println("result"+result);
//        List<BookSecondSort> bookSecondSorts = sortService.getSecondSort(bookSecondSort.getUpperName());
//        for(int i=0;i<bookSecondSorts.size();i++){
//            System.out.println(bookSecondSorts.get(i).toString());
//            System.out.println("-------------------");
//        }
    }

    @Test
    void AddressTest(){
        Address address = new Address();
        address.setAccount("黄小龙");
        address.setName("小胖");
        address.setPhone("18989892121");
        address.setLabel("家");
        address.setAddr("江西省南昌市");
        int result=addressService.setAddressOff(4);
        System.out.println("result:"+result);
        List<Address> addressesList = addressService.addressList(address.getAccount());
        for(int i=0;i<addressesList.size();i++){
            System.out.println(addressesList.get(i).toString());
            System.out.println("-----------------------");
        }
    }
    @Test
    void cartTest(){
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        Cart cart = new Cart();
        cart.setAccount("黄哈哈哈哈龙");
//        cart.setIsbn("3ewe2434522335");
        cart.setNum(2);
        cart.setAddTime(timeStamp);
//        int result = cartService.addProduct(cart);
//        System.out.println(result);
//        List<Cart> cartList = cartService.getCartsByPage(cart.getAccount(),0,2);
//        for(int i=0;i<cartList.size();i++){
//            System.out.println(cartList.get(i).toString());
//            System.out.println("--------------------------");
//        }
    }

    @Test
    void TestEnum(){
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.SUBMIT;
        System.out.println(orderStatusEnum.getIndex()+":"+orderStatusEnum.getName());
    }

    @Test
    void TestPublish(){
        Publish publish = new Publish();
        publish.setName("电子工业出版社");
//        publish.isShowPublish(false);
        publish.setRank(124);
        int result = publishService.addPublish(publish);
        System.out.println("result"+result);
        List<Publish> publishList=publishService.getPublishByPage(0,4);
        for(int i=0;i<publishList.size();i++){
            System.out.println(publishList.get(i).toString());
            System.out.println("-------------------------");
        }
    }

    @Test
    void TestRecommend(){
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        Recommend recommend = new Recommend();
        recommend.setBookId(1);
        recommend.setRank(20);
        recommend.setAddTime(timeStamp);
//        int result = bookService.addToNewProduct(recommend);
//        int result = bookService.modifyNewProductRank(recommend.getisbn(),recommend.getRank());
//        int result = bookService.deleteFromNewProduct(recommend.getBookId());
//        System.out.println("result"+result);
//        List<Recommend> list=bookService.getNewProductsByPage(0,3);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i).toString());
//            System.out.println("----------------------");
//        }
    }

    @Test
    void TestBookTopic(){
//        BookTopicList bookTopicList=new BookTopicList();
//        bookTopicList.setTopicName("书小单");
//        bookTopicList.setCover("黄龙");
//        bookTopicList.setSubTitle("留小意");
//        bookTopicList.setId(2);
//        int result = bookService.delFromBookTopicList(bookTopicList.getTopicName());
//        System.out.println(result);
//        try{
////            int result = bookService.modifyBookTopicList(bookTopicList);
//            System.out.println(result);
//        }catch(Exception e) {
//            System.out.println("不能插如");
//        }
//        List<BookTopicList> list=bookService.getBookTopicListsByPage(0,3);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i).toString());
//            System.out.println("-------------");
//        }
//        BookTopicList bookTopicList1=bookService.getBookTopicList("书单");
//        System.out.println(bookTopicList1.toString());

        SubBookTopic subBookTopic = new SubBookTopic();
//        subBookTopic.setIsbn("黄小龙湖龙");
        subBookTopic.setRecommendReason("狗胖");
//        subBookTopic.setTopicName("小小胖");
//        int result = bookService.delFromSubBookTopic(subBookTopic.getTopicName(),subBookTopic.getIsbn());
//        System.out.println(result);

//        SubBookTopic subBookTopic1=bookService.getSubBookTopic(subBookTopic.getTopicName(),subBookTopic.getIsbn());
//        System.out.println(subBookTopic1.toString());

    }
    private String basePath="D://ITsoftware//IDEA//data//Vue//book_01//";
    private String bookPath="static//image//";

    @Autowired
    BookMapper mapper;


    @Autowired
    TopicMapper topicMapper;


    @Test
    public void  testTopicImg(){
        BookTopic bookTopic = new BookTopic();
        bookTopic.setId(7);
        bookTopic.setPut(true);
        int result = topicMapper.modifyBookTopic(bookTopic);
        System.out.println(result);
    }

    @Test
    public void testBatch(){
        List<SubBookTopic> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            SubBookTopic bookTopic = new SubBookTopic();
            bookTopic.setTopicId(1);
            bookTopic.setBookId(i);
            list.add(bookTopic);
        }
        int result = topicMapper.batchDelSubTopic(list);
        System.out.println(result);
    }

    @Test
    public void testTable(){
//        List<Map<String,Object>> list = topicMapper.getSubTopicResList(1);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i).toString());
//        }
        List<Book> list = topicMapper.getRecBookList(55);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }

    @Autowired
    OrderMapper orderMapper;
    @Test
    public void order(){
        OrderDto orderDto = orderMapper.findOrderDto(1);
//        System.out.println(orderDto.toString());
//
    }

    @Autowired
    BookMapper bookMapper;

    @Test
    public void orderDetail(){
//
//        List<OrderDetailDto> orderDetailDtoList = orderMapper.findOrderDetailDtoList("1");
//        System.out.println(orderDetailDtoList.size());
//        for(OrderDetailDto orderDetailDto:orderDetailDtoList){
//            System.out.println(orderDetailDto.toString());
//            System.out.println("==================================");
//        }
        Date date = new Date();
        System.out.println(date);
        int[] ids = {55,56,57};
//        List<Book> bookList =  bookMapper.getBatchBookList(ids);
//        for(Book book:bookList){
//            System.out.println(book.toString());
//        }
    }

    @Test
    void testOrder(){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBookId(55);
        orderDetail.setNum(3);
        orderDetail.setOrderId("2");
        orderDetail.setPrice(23);
        orderDetailList.add(orderDetail);
        orderMapper.batchAddOrderDetail(orderDetailList);//批量添加订单明细
    }

    @Autowired
    ExpenseMapper expenseMapper;
    @Test
    void expenseTest(){
        Expense expense = new Expense();
        expense.setOrderId("4c97e44b83484b30bea53281ad3c8ccd");
        expense.setCoupon(1);
        expenseMapper.addExpense(expense);
    }


    @Test
    void cartTest1(){
        OrderDto orderDto = new OrderDto();
        orderDto = orderMapper.findOrderDto(31);
        List<OrderDetailDto> orderDetailDtoList  = orderMapper.findOrderDetailDtoList(orderDto.getOrderId());
        System.out.println("===orderDetailDtoList.size()==="+orderDetailDtoList.size()+"======");
        for(int i=0;i<orderDetailDtoList.size();i++){
            System.out.println("======="+orderDetailDtoList.get(i).toString()+"=====");
        }
    }


//    ==========================redis的测试==============================================
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void addTest(){

//        redisTemplate.opsForZSet().add("huang","huang",1);
//        redisTemplate.opsForZSet().add("huang","long",2);
//        redisTemplate.opsForZSet().add("huang","lon",3);
//        redisTemplate.opsForZSet().add("huang","lo",4);
//        redisTemplate.opsForZSet().add("huang","l",5);
//        redisTemplate.opsForZSet().add("huang","huangLong",6);
//        redisTemplate.opsForZSet().add("huang","小huangLong",2);

        User user = new User();
        user.setId(2);
        user.setAccount("hhh");
        redisTemplate.opsForZSet().add("userList",user,2);
//
//        Set user1 = redisTemplate.opsForZSet().range("userList", 0, 3);
//        System.out.println(user1);
        if(redisTemplate.hasKey("userList")){
            Set userList = redisTemplate.opsForZSet().range("userList", 0, 3);
            List<User> userList1 = new ArrayList<>(userList);
            System.out.println(userList1);
        }


//
//        redisTemplate.opsForZSet().remove("userList",user);
//
//        user1 = redisTemplate.opsForZSet().range("userList", 0, 3);
//        System.out.println(user1);

//
//        Set<ZSetOperations.TypedTuple> set1 = new HashSet<ZSetOperations.TypedTuple>();
//        int j = 9;
//        for (int i = 1; i <= 3; i++) {
//            User user = new User();
//            user.setSalary(200);
//            user.setUserName("黄龙");
//            user.setPassWord("123456");
//            user.setUid(i+3);
//            //计算分数和值
//            Double score1 = Double.valueOf(i);
//            String value1 = "x" + i;
//            //使用Spring提供的默认TypedTuple----DefaultTypedTuple
//            ZSetOperations.TypedTuple typedTuple1 = new DefaultTypedTuple(user, score1);
//            set1.add(typedTuple1);
//        }
//        //将元素插入有序集合zset1
//        redisTemplate.opsForZSet().add("zset1", set1);
//        Set zset1 = redisTemplate.opsForZSet().range("zset1", 0, 10);
//        System.out.println(zset1);



//        for(int i=10;i<13;i++){
//            User user = new User();
//            user.setUid(i);
//            user.setSalary(200);
//            user.setUserName("黄龙");
//            user.setPassWord("123456");
//            redisTemplate.opsForZSet().add("cat",user,new Date().getTime());
//        }
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("===========哈哈哈哈哈哈============");
//                for (int i = 0; i < 5; i++) {
//                    Timestamp timestamp = new Timestamp(new Date().getTime());
//                    System.out.println(timestamp.getTime());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();
    }


    @Test
    void bookTest(){
        Book book = new Book();
        book.setAuthor("黄了");
        book.setRank(10);
        book.setBookName("平凡的世界");
        book.setisbn("9994933234899");
        book.setPublish("人民出版社");
        book.setBirthday(new Timestamp(new Date().getTime()));
        book.setMarketPrice(123.0);
        book.setPrice(12.0);
        book.setStock(2);
        book.setDescription("hhh");
        book.setPut(true);
        book.setNewProduct(true);
        book.setRecommend(true);
//        bookService.addBook(book);
//        List<Book> booksByPage = bookService.getBooksByPage(1, 5);
//        System.out.println(booksByPage);

        Book book1 = bookService.getBook(120);
        System.out.println(book1);
    }

}
