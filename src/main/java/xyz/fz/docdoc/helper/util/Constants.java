package xyz.fz.docdoc.helper.util;

public class Constants {
    public static final String HELPER_NGINX_CONF_TEMPLATE = "" +
            "worker_processes  2;\n" +
            "\n" +
            "events {\n" +
            "    worker_connections  1024;\n" +
            "}\n" +
            "\n" +
            "http {\n" +
            "    upstream program_server {\n" +
            "        server @programAddress@;\n" +
            "    }\n" +
            "    upstream mock_server {\n" +
            "        server @mockAddress@;\n" +
            "    }\n" +
            "\n" +
            "    index              index.html index.htm index.php;\n" +
            "    include            mime.types;\n" +
            "    default_type       application/octet-stream;\n" +
            "    sendfile           on;\n" +
            "    keepalive_timeout  65;\n" +
            "\n" +
            "    server {\n" +
            "        listen         @localPort@;\n" +
            "        server_name    localhost;\n" +
            "        root           html;\n" +
            "\n" +
            "        location / {\n" +
            "            add_header Access-Control-Allow-Origin http://localhost:8080;\n" +
            "            add_header Access-Control-Allow-Methods 'GET, POST, OPTIONS';\n" +
            "            add_header Access-Control-Allow-Headers 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization';\n" +
            "            add_header Access-Control-Allow-Credentials true;\n" +
            "            if ($request_method = 'OPTIONS') {\n" +
            "                return 204;\n" +
            "            }\n" +
            "            proxy_redirect          off;\n" +
            "            proxy_set_header        Host            @programHost@;\n" +
            "            proxy_set_header        X-Real-IP       $remote_addr;\n" +
            "            proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;\n" +
            "            client_max_body_size    10m;\n" +
            "            client_body_buffer_size 128k;\n" +
            "            proxy_connect_timeout   90;\n" +
            "            proxy_send_timeout      90;\n" +
            "            proxy_read_timeout      90;\n" +
            "            proxy_buffers           32 4k;\n" +
            "            proxy_pass              http://program_server;\n" +
            "        }\n" +
            "\n" +
            "@mockLocationList@" +
            "\n" +
            "        error_page 500 502 503 504 /50x.html;\n" +
            "        location = /50x.html {\n" +
            "            root   html;\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
    public static final String MOCK_LOCATION_TEMPLATE = "" +
            "        location @mockLocation@ {\n" +
            "            add_header Access-Control-Allow-Origin http://localhost:8080;\n" +
            "            add_header Access-Control-Allow-Methods 'GET, POST, OPTIONS';\n" +
            "            add_header Access-Control-Allow-Headers 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization';\n" +
            "            add_header Access-Control-Allow-Credentials true;\n" +
            "            if ($request_method = 'OPTIONS') {\n" +
            "                return 204;\n" +
            "            }\n" +
            "            proxy_redirect          off;\n" +
            "            proxy_set_header        Host            @mockHost@;\n" +
            "            proxy_set_header        X-Real-IP       $remote_addr;\n" +
            "            proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;\n" +
            "            proxy_set_header        owner           @mockUsername@;\n" +
            "            @set_restful_header@\n" +
            "            client_max_body_size    10m;\n" +
            "            client_body_buffer_size 128k;\n" +
            "            proxy_connect_timeout   90;\n" +
            "            proxy_send_timeout      90;\n" +
            "            proxy_read_timeout      90;\n" +
            "            proxy_buffers           32 4k;\n" +
            "            proxy_pass              http://mock_server;\n" +
            "        }\n";
}
