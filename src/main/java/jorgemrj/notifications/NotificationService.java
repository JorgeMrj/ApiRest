package jorgemrj.notifications;

import io.reactivex.rxjava3.core.Observable;

public interface NotificationService<T> {
    void notify(Notification<T> notification);

    Observable<Notification<T>> getNotificationsSubject();
}