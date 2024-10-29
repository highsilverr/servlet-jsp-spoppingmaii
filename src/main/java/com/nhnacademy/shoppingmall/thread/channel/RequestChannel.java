package com.nhnacademy.shoppingmall.thread.channel;


import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;

import java.util.LinkedList;
import java.util.Queue;

public class RequestChannel {
    private final Queue<ChannelRequest> queue;
    private final int queueMaxSize;

    public RequestChannel(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
        this.queue = new LinkedList<>();
    }

    public synchronized ChannelRequest getRequest() throws InterruptedException {

        //todo#14-3 queue가 비어있다면 대기합니다.
        while (queue.isEmpty()) {
            wait(); // 다른 스레드가 요청을 추가할 때까지 대기
        }

        //todo#14-4 queue에서 request 반환합니다.
        // 큐에서 요청 반환
        ChannelRequest request = queue.poll(); // 요청을 큐에서 제거하고 반환
        notifyAll(); // 대기 중인 스레드를 깨운다
        return request;

    }

    public synchronized void addRequest(ChannelRequest request) throws InterruptedException {
        // 큐가 가득 차 있다면 요청이 소비될 때까지 대기
        while (queue.size() >= queueMaxSize) {
            wait(); // 큐가 비어질 때까지 대기
        }

        // 큐에 요청을 추가하고 대기 중인 스레드를 깨운다
        queue.offer(request); // 요청을 큐에 추가
        notifyAll(); // 대기 중인 스레드를 깨운다
    }

}
