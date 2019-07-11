package java_jdk.springannotation;


/**
 * Created by wb-zj373670 on 2018/5/2.
 */
public class SimpleClassPathXMLApplicationContext {
//    Logger logger = Logger.getLogger(SimpleClassPathXMLApplicationContext.class);
//
//    List<BeanDefine> beanList = new ArrayList<>();
//    Map<String, Object> sigletons = new HashMap<String, Object>();
//
//    public SimpleClassPathXMLApplicationContext(String fileName){
//        this.readXML(fileName);
//        this.instanceBean();
//        this.annotationInject();
//    }
//
//    public void readXML(String fileName){
//        Document document = null;
//        SAXReader saxReader = new SAXReader();
//        try{
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            document = saxReader.read(classLoader.getResourceAsStream(fileName));
//            Element beans = document.getRootElement();
//            for(Iterator<Element> beansList = beans.elementIterator(); beansList.hasNext();){
//                Element element = beansList.next();
//                BeanDefine bean = new BeanDefine(element.attributeValue("id"), element.attributeValue("class"));
//                beanList.add(bean);
//
//            }
//        }catch (Exception e){
//            System.out.println("读取配置文件出错");
//        }
//    }
//
//    public void instanceBean(){
//        for(BeanDefine bean : beanList){
//            try{
//                sigletons.put(bean.getId(), Class.forName(bean.getClassName()).newInstance());
//            }catch (Exception e){
//                System.out.println("初始化bean出错");
//            }
//        }
//    }
//
//    public void annotationInject(){
//        for(String beanName:sigletons.keySet()){
//            Object bean = sigletons.get(beanName);
//            if(bean != null){
//                this.propertyAnnotation(bean);
//                this.fieldAnnotation(bean);
//            }
//        }
//    }
//
//    public void propertyAnnotation(Object bean){
//        try{
//            PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
//            for(PropertyDescriptor proderdesc : ps){
//                Method setter = proderdesc.getWriteMethod();
//                if(setter != null && setter.isAnnotationPresent(ZxfResource.class)){
//                    ZxfResource resource = setter.getAnnotation(ZxfResource.class);
//                    String name = "";
//                    Object value = null;
//                    if(resource.name()!=null && !"".equals(resource.name())){
//                        name = resource.name();
//                        value = sigletons.get(name);
//                    }else{
//                        for(String key : sigletons.keySet()){
//                            if(proderdesc.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())){
//                                value = sigletons.get(key);
//                            }
//                        }
//
//                    }
//                    setter.setAccessible(true);
//                    setter.invoke(bean,value);
//                }
//            }
//        }catch (Exception e){
//            System.out.println("set方法解析异常");
//        }
//    }
//
//   //处理字段上的注解
//    public void fieldAnnotation(Object bean){
//        try{
//            Field[] fields = bean.getClass().getFields();
//            for(Field f : fields){
//                if(f != null && f.isAnnotationPresent(ZxfResource.class)){
//                    ZxfResource resource = f.getAnnotation(ZxfResource.class);
//                    String name = "";
//                    Object value = null;
//                    if(resource.name() != null && "".equals(resource.name())){
//                        name = resource.name();
//                        value = sigletons.get(name);
//                    }else {
//                        for(String key : sigletons.keySet()){
//                            if(f.getType().isAssignableFrom(sigletons.get(key).getClass())){
//                                value = sigletons.get(key);
//                                break;
//                            }
//                        }
//                    }
//                    f.setAccessible(true);
//                    f.set(bean, value);
//                }
//            }
//        }catch (Exception e){
//            System.out.println("字段注解解析失败");
//        }
//    }
//
//    public Object getBean(String beanId){
//        return sigletons.get(beanId);
//    }
//
//    public static void main(String[] args) {
//        SimpleClassPathXMLApplicationContext context = new SimpleClassPathXMLApplicationContext("configAnnotation.xml");
//        UserServiceImpl service = (UserServiceImpl)context.getBean("userService");
//        service.show();
//    }
}
