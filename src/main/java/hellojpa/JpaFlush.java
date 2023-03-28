package hellojpa;

import org.hibernate.FlushMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class JpaFlush {

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

            //flush -> dirty checking -> sql update

            //em.flush(); - 직접
            //transaction - 자동
            //jpql - 자동

            Member member = new Member(15L, "memberNew");
            System.out.println("=============");
            em.persist(member);
            System.out.println("=============");
//            em.flush();
            System.out.println("=============");

            //FlushModeType.AUTO - default - 권장
            //FlushModeType.COMMIT - 커밋 시만 flush


            //jpql 중간에 실행 시, persist에 저장된 것은 실행이 안됨 -> 실행 전 자동 실행시킴


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
