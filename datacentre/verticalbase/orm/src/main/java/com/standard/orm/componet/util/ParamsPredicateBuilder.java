package com.standard.orm.componet.util;

import com.standard.orm.componet.constant.Expression;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParamsPredicateBuilder {


   public static <T> Predicate getPredicate(Root<T> root, CriteriaBuilder builder, Iterable<Expression> iterable) {
        return ObjectUtils.isEmpty(iterable)
                ? null
                :builder.and(getPredicates(root,builder,iterable).toArray(new Predicate[0]));
    }
   private static List<Predicate> getPredicates(Root<?> root, CriteriaBuilder builder, Iterable<Expression> iterable) {
        List<Predicate> predicates = new ArrayList<>();
        Iterator<Expression> iterator =iterable.iterator();
        while (iterator.hasNext()){
            Expression expression =iterator.next();
            predicates.add(getPredicatesByExpression(root,builder,expression));
        }
        return predicates;
    }
   private static Predicate getPredicatesByExpression(Root<?> root, CriteriaBuilder builder, Expression expression) {
      Predicate predicate = null;
        if (expression.getUnique()==1){
            Path path =PathUtil.getPath(root,expression.getKey(), JoinType.LEFT);
            Object value =expression.getValue();
            switch (expression.getType()){
                case "like":
                    predicate = builder.like(path,"%/"+value+"%",'/');
                    break;
                case "notLike":
                    predicate = builder.notLike(path,"%/"+value+"%",'/');
                    break;
                case "lt":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.lessThan(path,getTime(value.toString(),path))
                            :builder.lt(path,new BigDecimal(value.toString()));
                    break;
                case "le":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.lessThanOrEqualTo(path,getTime(value.toString(),path))
                            :builder.le(path,new BigDecimal(value.toString()));
                    break;
                case "gt":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.greaterThan(path,getTime(value.toString(),path))
                            :builder.gt(path,new BigDecimal(value.toString()));
                    break;
                case "ge":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.greaterThanOrEqualTo(path,getTime(value.toString(),path))
                            :builder.ge(path,new BigDecimal(value.toString()));
                    break;
                case "eq":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.equal(path,getTime(value.toString(),path))
                            :builder.equal(path,value);
                    break;
                case "notEq":
                    predicate = path.getJavaType().getSimpleName().contains("Date")
                            ?builder.notEqual(path,getTime(value.toString(),path))
                            :builder.notEqual(path,value);
                    break;
                case "in":
                    CriteriaBuilder.In in = builder.in(path);
                    Object[] objects =(Object[])value;
                    for (int i = 0; i <objects.length ; i++) {
                        in.value(objects[i]);
                    }
                    predicate =in;
                    break;
                case "notIn":
                    CriteriaBuilder.In inn = builder.in(path);
                    Object[] objects1 =(Object[])value;
                    for (int i = 0; i <objects1.length ; i++) {
                        inn.value(objects1[i]);
                    }
                    predicate =builder.not(inn);
                    break;
                case "isNull":
                    predicate= builder.isNull(path);
                    break;
                case "isNotNull":
                    predicate= builder.isNotNull(path);
                    break;
                /*case "isMember":
                    Object[] members =(Object[])value;
                    predicate =builder.isMember(value, Arrays.asList(members))
                    break;
                case "isNotMember":
                    Object[] notMembers =(Object[])value;
                    predicate =builder.isNotMember(value, Arrays.asList(notMembers));
                    break;*/
            }
        }else {
            List<Expression> list = expression.getExpressions();
            List<Predicate> predicateList =new ArrayList<>();
            list.forEach(ele ->{
                predicateList.add(getPredicatesByExpression(root, builder,  ele));
            });
            predicate ="or".equalsIgnoreCase(expression.getJoinType())?
                    builder.or(predicateList.toArray(new Predicate[0])):
                    builder.and(predicateList.toArray(new Predicate[0]));
        }
        return predicate;
    }

   public  static Comparable  getTime(String timeStr,Path path){
        Class timeclass =path.getJavaType();
        Comparable comparable=null;
        String pattern= timeStr.matches("\\d{4}-\\d{2}-\\{d}{2}\\s+\\d{2}:\\d{2}:\\d{2}")?
                "yyyy-MM-dd HH:mm:ss":"yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            if (LocalDateTime.class.isAssignableFrom(timeclass)){
                comparable=  LocalDateTime.parse(timeStr,formatter);
            }else if (LocalDate.class.isAssignableFrom(timeclass)){
                comparable =LocalDate.parse(timeStr,formatter);
            }else if(Date.class.isAssignableFrom(timeclass)){
                comparable =new SimpleDateFormat(pattern).parse(timeStr);
            }else if (ZonedDateTime.class.isAssignableFrom(timeclass)){
                LocalDateTime localDateTime =   LocalDateTime.parse(timeStr,formatter);
                ZoneId zone = ZoneId.of(TimeZone.getDefault().getID());
                comparable = localDateTime.atZone(zone);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  comparable;
    }
}
