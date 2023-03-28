package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaJpql {

    public static void main(String[] args){

        //일괄적인 한 단위 작업마다 생성해야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션 호출
        //jpa 모든 작업은 트랜잭션 안에서 작업
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 정석
        try{
            //code


            //jpql
            //List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            //객체 대상으로 조회 - 테이블이 아닌 객체 개념

            //paging
            //시작값 ~ 최대값
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(1).setMaxResults(10).getResultList();

            for(Member member : result){
                System.out.println("member.name? == " + member.getName());
            }

        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
