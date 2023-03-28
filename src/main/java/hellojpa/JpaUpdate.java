package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUpdate {

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

            //update
            Member findMember = em.find(Member.class, 1L); //pk 찾기
            System.out.println("find Member.id = " + findMember.getId());
            System.out.println("find Member.name = " + findMember.getName());

            findMember.setName("HelloJPA");

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
