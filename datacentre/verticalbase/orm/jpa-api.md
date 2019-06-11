        查询语言：Query Language
        条件表达式：where+having+on
        输入参数：支持位置参数 ?1，和命名参数 :userName，但这两者不能混用
        条件组合：属性导航：user.userName，一元运算 + -，逻辑操作 NOT AND OR，
        比较Comparison：= > >= < <= <>
        区间Between：[NOT] BETWEEN，支持数字、字符串、日期
        集合In：[NOT] IN，f in ('a', 'b') 等价于 f='a' or f='b'
        相似Like：[NOT] LIKE，支持单个字符_、多个字符%、转义字符\_
        空值Null：IS [NOT] NULL，
        空集合：IS [NOT] EMPTY，select o from Order o where o.lineItems is empty，
        集合成员：[NOT] MEMBER OF，select p from Person p where 'Joe' member of p.nicknames;
        存在性Exist：[NOT] EXISTS，select emp from Employee emp where exists ( select spouseEmp from Employee spouseEmp where spouseEmp = emp.spouse );//spouse配偶
        全部或任意：All+Any+Some，select emp from Employee where emp.salary > All { select m.salary from Manager m where m.department=emp.department };//部门高新员工
        子查询Subquery：select goodCustomer from Customer goodCustomer where goodCustomer.balanceOwed < ( select avg(c.balanceOwed)/2.0 from Customer c );//优质客户
        原生标准操作
        字符串：concat，substr，trim，lower，
        算术：abs，sqrt，mod，size，index（select w.name from Course c join c.studentsWaitlist w where c.name='Calculus' and index(w)=0;）
        聚合：count，max，min，avg，sum，distinct
        日期：current_date，current_time，current_timestamp
        过程调用：select c from Customer c where function( 'hasGoodCredit', c.balance, c.creditLimit );
        分支语句：update Employee e set e.salary = 
        case e.rating when 1 then e.salary*1.1
                              when 2 then e.salary*1.05
                              else e.salary*1.01
        end
        类型判断：select e from Employee e where type(e) in ( Exempt, Contractor )
        分组和结果限定
        select c.status, avg(c.filledOrderCount), count(c) from Customer c group by c.status having c.status in ( 1, 2 );//统计状态1和2的客户信息
        select c, count(o) from Customer c Join c.orders o group by c having count(o) >= 5;//查询订单数大于等于5的客户
        排序
        select o from Customer c JOIN c.orders o JOIN c.address a where a.state='CA' order by o.quantity DESC, o.totalcost;//总量倒序，花费升序
        批量更新和删除
        delete from Customer c where c.status='inactive' and c.orders is empty;//删除未激活且没有订单的用户
        update Employee e set e.address.building=22 where e.address.building=14 and e.project='Java EE';//修改Java EE项目组成员的地址
        查询条件：Criteria，标准条件
        CriteriaBuilder，@PersistenceUnit EntityManagerFactory.getCriteriaBuilder(); 或 @PersistenceContext EntityManager.getCriteriaBuilder();
        CriteriaQuery<T> createQuery(Class<T> resultClass);//创建查询，返回结果类型为resultClass
        CriteriaQuery<Tuple> createTupleQuery();//创建查询，返回元组类型，Tuple包含多个TupleElements
        CriteriaUpdate<T> createCriteriaUpdate(Class<T> targetEntity);//创建对targetEntity的批量修改操作
        CriteriaDelete<T> createCriteriaDelete(Class<T> targetEntity);//创建对targetEntity的批量删除操作
        CompoundSelection<Tuple> tuple(Selection<?>... selections);//创建tupe-valued查询
        CompoundSelection<Object[]> array(Selection<?>... selections);//创建array-valued查询
        Order asc(Expression<?> x);//创建表达式值的升序，desc为降序
        Expression<Double> avg(Expression<N> x);//计算平均值，还有count[Distinct]，sum，max+min（数字），greatest+least（字符串、日期等），
        Predicate exists(Subquery<?> subquery);//判断子查询是否存在，还有all，some，any，isTrue，isFalse，is[Not]Null
        Predicate and(Expression<Boolean> x, Expression<Boolean> y);//逻辑与，还有or，not
        Predicate or(Predicate... restrictions);//多个条件或，还有与and
        Predicate conjunction();//条件与，没有时返回true；还有disjunction，条件或，没有时返回false；
        Predicate equal(Expression<?> x, Expression<?> y);//判断是否相等，还有greaterThan[OrEqualTo]（Comparable），between，gt+ge+lt+le（Number）
        Predicate notEqual(Expression<?> x, Object value);//判断是否不等，还有lessThan[OrEqualTo]，like+notLike
        Expression<Number> sum(Expression<?> x, N y);//算术运算，还有neg+abs+prod积+diff差+quot商+mod模+sqrt平方根+concat+substring+trim+upper+length+locate
        Expression<Long> toLong(Expression<?> x);//类型转换，还有Integer+Float+Double+BigDecimal+BigInteger+String
        Expression<T> literal(T value);//转换常量值，
        Predicate is[Not]Empty(Expression<c> collection);//测试集合非空，还有size+is[Not]Member，q.where(cb.isEmpty(order.get("lineItems"))); => where order.lineItems is empty
        Expression<Collection<V>> values(M map);//从map的values()创建集合条件，还有keys
        Expression<java.sql.Date> currentDate();//当前日期，还有java.sql.Time=currentTime，java.sql.Timestamp=currentTimestamp
        In<T> in(Expression<T> expression);//q.having( cb.in( customer.get("status").value(1).value(2) ) ); => having c.status in ( 1,2 )
        CriteriaQuery -> AbstractQuery
        Root<X> from(Class<X> entityClass);//获得查询根，或与已有根计算笛卡尔积
        AbstractQuery<T> where(Predicate... restrictions);//限定条件（替换之间的条件）
        AbstractQuery<T> groupBy(Expression<?>... grouping);//分组，还有having+orderBy
        CriteriaQuery<T> select(Selection<?> selection);//设置选择结果
        CriteriaQuery<T> multiselect(Selection<?>... selections);//多个选择结果
        执行复杂条件查询
        @PersistenceContext EntityManager em;
        TypedQuery<T> query = em.createQuery(CriteriaQuery<T> cq);//创建查询对象
        query.setFirstResult().setMaxResults().getResultList();//获得查询结果
        CriteriaUpdate+CriteriaDelete，有from+where
        CriteriaUpdate<T> set(Path attribute, X value);//更新属性
        
        update customer c set c.status='outstanding' where c.balance<1000;
        CriteriaUpdaate<Customer> q=cb.createCriteriaUpdate(Customer.class);
        Root<Customer> c=q.from(Customer.class);
        q.set(c.get("status"), "outstanding")
        .where( cb.lt(c.get("balance"), 1000) );
        Root实体根+Join表连接 -> From查询 -> Path路径+Predicate与或 -> Expression表达式 -> Selection选择返回值
        Selection alias(String alias);//设置别名
        Predicate isNull();//是否空，还有isNotNull+
        Predicate in(Object... values);//集合元素，
        Path<Y> get(String attributeName);//获取路径
        Join join(String attributeName, JoinType type);//链接属性表，还有joinCollection，joinSet，joinList（t.index()限定多个对象的个数），joinMap（t.key()限定对象映射的key）
        Join on(Predicate... restrictions);//表链接
        查询样例，最好先组织SQL和JPQL语句，然后再翻译为对象方法调用
        获取全部或限定条件，select c from customer c where c.name="Jack";
        CriteriaQuery<Customer> q = CriteriaBuilder.createQuery(Customer.class);//返回类型为Customer
        Root<Customer> customer = q.from(Customer.class);//查询表Customer
        q.select(customer);//查询整个Customer对象
        q.where(cb.equal( customer.get("name"), 'Jack'));//限定name="Jack"的对象
        连接统计，select s.name, count(p) from Supplier s left join s.products on p.status='inStock' group by s.name;
        CriteriaQuery<Tuple> q = cb.createTupleQuery();//配合后面的multiselect
        Root<Supplier> s = q.from(Supplier.class);//
        Join<Supplier, Product> p = s.join("products", JoinType.LEFT);//连接产品表
        p.on(cb.equal(p.get("status"), "inStock"));//限定连接条件，s.join("products", JoinType.LEFT).on(cb.equal(p.get("status"), "inStock"));
        q.groupBy( s.get("name") );
        q.multiselect( s.get("name"), cb.count(p) );//等价于q.select(cb.tuple(s.get("name"), cb.count(p)));，还有cb.array，cb.construct(Type.class, ... args)
        深度获取fetch join，select d from Department d left join fetch d.employees where d.deptno=1;
        CriteriaQuery<Department> q = cb.createQuery(Department.class);
        Root<Department> d = q.from(Department.class);
        d.fetch("employees", JoinType.LEFT);//查询结果中d.employees有值
        q.where( cb.equal(d.get("deptno"), 1) );
        q.select(d);
        路径导航，select p.vendor from Employee e join e.contactInfo.phones p where e.contactInfo.address.zipcode="95054";
        CriteriaQuery<Vendor> q = cb.createQuery<Vendor.class>;//返回结果为Vendor
        Root<Employee> e = q.from(Employee.class);//从Employee开始查询
        Join<ContactInfo, Phone> phone = e.join("contactInfo").join("phones");//连接多张表
        q.where( cb.equal(e.get("contactInfo").get("address").get("zipcode"), "95054") );
        q.select( phone.get("vendor") );
        类型转换，select b.ISBN from Order o join treat(o.product AS Book) b;
        CriteriaQuery<String> q = cb.createQuery(String.class);
        Root<Order> order = q.from(Order.class);
        Join<Order, Book> book = cb.treat( order.join("product"), Book.class);
        q.select( book.get("isbn") );
        分支查询，
        select e.name ,
        case when e.rating=1 then e.salary*1.1
                when e.rating=2 then e.salary*1.2
                else e.salary&1.01
        from Employee e where e.department.name='Engineering';
        CriteriaQuery<Tuple> q=cb.createTupleQuery();
        Root<Employee> e=q.from(Employee.class);
        q.where( cb.equal(e.get("department").get("name"), "Engineering") );
        q.multiselect( e.get("name"), cb.selectCase()
                                                             .when(cb.equal(e.get("rating"), 1), cb.prod(e.get("salary"), 1.1))
                                                             .when(cb.equal(e.get("rating"), 2), prod(e.get("salary"), 1.2))//这里需要常量的话调用cb.literal("value")
                                                             .otherWise(cb.prod(e.get("salary"), 1.01)) );
        参数
        select c from Customer c where c.status = :stat;
        CriteriaQuery<Customer> q=cb.createQuery(Customer.class);
        Root<Customer> c=q.from(Customer.class);
        ParameterExpression<Integer> param=cb.parameter(Integer.class, "stat");
        q.select(c).where(cb.equal(c.get("status"), param));
        子查询，select goodCustomer from Customer goodCustomer where goodCustomer.balanceOwed < ( select avg(c.balanceOwed) from Customer c );
        CriteriaQuery<Customer> q=cb.createQuery(Customer.class);
        Root<Customer> goodCustomer=q.from(Customer.class);
        Subquery<Double> sq=q.subquery(Double.class);//子查询返回double值
        Root<Customer> customer=sq.from(Customer.class);
        q.where(cb.lt(goodCustomer.get("balanceOwed"), sq.select(cb.avg(customer.get("balanceOwed")))));//goodCustomer.balanceOwed < avg(customer.balanceOwed)
        q.select(goodCustomer);
        
        select emp from Employee emp where emp.salary > all ( select m.salary from Manager m where m.department=emp.department );
        CriteriaQuery<Employee> q=cb.createQuery(Employee.class);
        Root<Employee> emp=q.from(Employee.class);
        Subquery<BigDecimal> sq=q.subquery(BigDecimal.class);
        Root<Manager> manager=sq.from(Manager.class);
        sq.select(manager.get("salary"));
        sq.where(cb.equals(manager.get("department"), emp.get("department")));
        q.select(emp).where(cb.gt(emp.get("salary"), cb.all(sq)));
        EntityManager
        获得EntityManager
        @PersistenceContext EntityManager em;
        @PersistenceUnit EntityManagerFactory emf; emf.createEntityManager()//
        javax.persistence.Persistence.createEntityManagerFactory("order");//从persistence.xml文件读取unit配置