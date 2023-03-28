package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTransaction {

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
            //transaction
            Member member1 = new Member(10L, "X_LOG");
            Member member2 = new Member(11L, "Y_LOG");

            em.persist(member1);
            em.persist(member2);
            //jdbc 배치 - 실시간 쿼리에서는 이점은 없음
            //버퍼링 기능 - 이점 : 옵션으로 성능을 가지고 쓸 수 있음
            // 버퍼링을 모아서 한번에 라이트 가능

            System.out.println("=======================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
