package com.hzh.app.subscribe;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@Component
public class SubscribeStore implements Serializable {

    private Set<String> set = new HashSet<>();
    private Map<String, List<Notifyer>> store = new HashMap<>();
    private Lock lock = new ReentrantLock();


    public boolean subscribe(String key, String ip) {

        try {
            lock.lock();

            List<Notifyer> notifyers = store.getOrDefault(key, new ArrayList<>());
            store.put(key, notifyers);

            if (notifyers.isEmpty()) {
                IpNotifyer notifyer = new IpNotifyer();
                notifyers.add(notifyer);
            }

            notifyers.forEach(ele -> {
                if (ele instanceof IpNotifyer) {
                    ((IpNotifyer) ele).addIp(ip);
                }
            });


            return true;
        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean unSubscribe(String key, String ip) {

        try {
            lock.lock();

            List<Notifyer> notifyers = store.getOrDefault(key, new ArrayList<>());

            if (notifyers.isEmpty()) {
                return true;
            }

            notifyers.forEach(ele -> {
                if (ele instanceof IpNotifyer) {
                    ((IpNotifyer) ele).removeIp(ip);
                }
            });

            store.put(key, notifyers);

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(String key) {

        try {
            lock.lock();
            if (set.contains(key)) {
                set.remove(key);

                if (store.containsKey(key)) {
                    store.get(key).forEach(ele -> {
                        ele.removeEvent(key);
                    });
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean add(String key) {

        try {
            lock.lock();
            if (!set.contains(key)) {
                set.add(key);

                if (store.containsKey(key)) {
                    store.get(key).forEach(ele -> {
                        ele.addEvent(key);
                    });
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            lock.unlock();
        }
    }

}
