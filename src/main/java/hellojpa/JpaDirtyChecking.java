package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDirtyChecking {

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

            //jpa 목적 - 컬렉션 다루듯 데이터 다루는 것
            Member member = em.find(Member.class, 10L); // 검색
            member.setName("xxxx"); // 변경

            //em.persist(member); <- 사용할 필요 없음 :: 호출하지 않는게 정답 : 값만 변경
            //더티 체킹 = 변경 감지
            System.out.println("=======================");
            //commit -> flush -> snapshot(before) <-> entity(after)

            //값을 바꾼 시점에 커밋이 되면 변경이 된다.
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); //매니저가 내부적으로 물고 진행하기에 종료 시 닫아야 한다.
        }

        emf.close(); // 로직 종료시 닫는다.


    }
}
