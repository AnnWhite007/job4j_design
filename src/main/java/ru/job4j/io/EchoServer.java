package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2. Что такое Socket?
 * Сокет представляет собой комбинацию IP - адреса и порта и является одним концом двустороннего канала связи между двумя программами.
 * Java для работы в сети имеет специальный пакет java.net:
 * java.net.ServerSocket - класс реализует серверный сокет, который ожидает запросы, приходящие от клиентов по сети, и может отправлять ответ.
 * java.net.Socket - класс реализует клиентский сокет.
 * Соединение между java - сокетами устанавливается с помощью транспортного протокола TCP.
 * То есть используя сокеты мы можем передавать и получать информацию по установленному TCP - соединению.
 * Клиент - программа cURL.
 * Сервер: Вызов конструктора ServerSocket создает серверный сокет, привязанный к указанному порту.
 * Чтобы клиент мог узнать, где находится сервер ему нужен адрес и порт, 9000 - это порт. По умолчанию адрес будет localhost.
 * Вызов метода accept() заставляет программу ждать подключений по указанному порту,
 * работа программы продолжится только после подключения клиента.
 * После успешного подключения метод возвращает объект Socket, который используется для взаимодействия с клиентом.
 * С помощью объекта Socket программа может получить входной поток и может отправить данные в выходной поток.
 * cd c:\Tools\curl-7.82.0-win64-mingw\bin\
 * curl -i http://localhost:9000/?msg=Hello
 * Параметр -i указывает curl вывести всю информацию принятую от сервера.
 * Параметр http://localhost:9000/?msg=Hello - это адрес. Он состоит из протокола http.
 * Адреса localhost. Порта 9000. Параметров запроса /?msg=Hello.
 * На стороне сервера эти параметры можно получить. Первая строчка содержит параметры запроса.
 *
 * Задача:
 * Если клиент отправляет запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
 *
 * 3. Бот
 * Чтобы браузер понял наш ответ, мы также должны использовать структуру HTTP - сообщения.
 * Сначала запишем стартовую строку с кодом состояния, затем добавим пустую строку (\r\n\r\n), и только после этого записываем наше сообщение.
 *
 * Задача:
 * Клиент отправляет запросы, сервер должен ему отвечать.
 * msg=Hello > Hello.
 * msg=Exit > Завершить работу сервера.
 * msg=Any > What.
 *
 * 4. Slf4j - вывод exception.
 * Если в проекте используется логгер, то для вывода ошибок или отладочной информации нужно использовать только логгер.
 * Первый параметр - это сообщение, почему тут может быть исключение. Второй параметр - это объект исключения.
 */

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] msg = in.readLine().split(" ");
                    System.out.println(String.join(" ", msg));
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    if (msg[1].endsWith("?msg=Exit")) {
                        in.close();
                        out.close();
                        server.close();
                        socket.close();
                    } else if (msg[1].endsWith("?msg=Hello")) {
                        out.write("Hello, dear friend.\r\n\r\n".getBytes());
                    } else {
                        out.write("What\r\n\r\n".getBytes());
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("IOException", e);
        }
    }
}