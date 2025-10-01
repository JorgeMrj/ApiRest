package jorgemrj.notifications;

import ch.qos.logback.classic.Logger;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.slf4j.LoggerFactory;

public class NotificationServiceImpl<T> implements NotificationService<T> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final Subject<Notification<T>> notificacionesSubject;

    public NotificationServiceImpl() {
        logger.info("Inicializando Notificaciones");
        notificacionesSubject = BehaviorSubject.create();
    }

    public void notify(Notification<T> notification) {
        logger.info("Notificando: " + notification);
    }

    public Observable<Notification<T>> getNotificationsSubject() {
        return notificacionesSubject;
    }
}