package com.standard.orm.componet.util;

import com.standard.orm.componet.constant.Expression;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParamsPredicateBuilder {


    public static <T> Predicate getPredicate(Root<T> root, CriteriaBuilder builder, Iterable<Expression> iterable) {

        return  null;
    }
   private static List<Predicate> getPredicates(Root<?> root, CriteriaBuilder builder, Iterable<Expression> iterable) {
        List<Predicate> predicates = new ArrayList<>();
        Iterator<Expression> iterator =iterable.iterator();
        while (iterator.hasNext()){
            Expression expression =iterator.next();
        }


        return predicates;
    }
    private static Predicate getPredicatesByExpression(Root<?> root, CriteriaBuilder builder, Expression expression) {
      Predicate predicate = null;


        return predicate;
    }


}
