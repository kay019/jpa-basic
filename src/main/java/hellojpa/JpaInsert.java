package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaInsert {

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

            //insert
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);

            //delete
            //em.remove(findMember);

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    }
}
