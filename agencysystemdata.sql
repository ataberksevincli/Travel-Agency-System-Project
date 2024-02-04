PGDMP  +                    |            agencysystemdata    16.1    16.1      3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    24985    agencysystemdata    DATABASE     r   CREATE DATABASE agencysystemdata WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
     DROP DATABASE agencysystemdata;
                postgres    false            �            1259    24995    hotel    TABLE     �  CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying(55) NOT NULL,
    adress character varying(100) NOT NULL,
    mail character varying(75) NOT NULL,
    phone_number character varying(11) NOT NULL,
    star text NOT NULL,
    park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25002    hotel_pension    TABLE     �   CREATE TABLE public.hotel_pension (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type character varying(50) NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false            �            1259    25005    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    24994    otel_otel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    25063    otel_pension_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    25055    otel_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    25008    reservation    TABLE     �  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_name character varying(50) NOT NULL,
    guest_citizen_id character varying(11) NOT NULL,
    guest_mail character varying(11) NOT NULL,
    guest_phone character varying(11) NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25120    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying(50) NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    safe_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25123    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    25130    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25129    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225            '          0    24995    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, adress, mail, phone_number, star, park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    216   �&       (          0    25002    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    217   h'       )          0    25005    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    218   �'       *          0    25008    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    219   D(       -          0    25120    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, safe_box, projection) FROM stdin;
    public          postgres    false    222   a(       0          0    25130    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    225   �(       7           0    0    otel_otel_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.otel_otel_id_seq', 5, true);
          public          postgres    false    215            8           0    0    otel_pension_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.otel_pension_id_seq', 22, true);
          public          postgres    false    221            9           0    0    otel_season_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.otel_season_id_seq', 13, true);
          public          postgres    false    220            :           0    0    room_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.room_id_seq', 1, true);
          public          postgres    false    223            ;           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 7, true);
          public          postgres    false    224            �           2606    25062    hotel_pension otel_pension_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT otel_pension_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT otel_pension_pkey;
       public            postgres    false    217            �           2606    25001    hotel otel_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT otel_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.hotel DROP CONSTRAINT otel_pkey;
       public            postgres    false    216            �           2606    25060    hotel_season otel_season_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT otel_season_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT otel_season_pkey;
       public            postgres    false    218            �           2606    25052    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219            �           2606    25128    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    222            �           2606    25136    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    225            '   �   x����
�0���S�6� k��7�z�$H���u�,Uԧ�!|0�M����|��T�?X�,}N���>��!`����-����ɱ
%������G��JB/�[5��11'�*X�����{Iu2��j��>��~��� ,�A�%�1� ��:�Ә!}����0�U�ܮZAcV:f�8;�/�x7�fM      (   |   x�34�4��))Jt�H�)-�,K�24�4�t+��	H�+����24*	8��d��g�d䗖8�$�g��p�s�r:�&f�%�pZp"s-9M�-02�"f�柗S锚�edT�0#F��� �63�      )   @   x�-��	 1��d5� �t�9J���RT�$(�3�p�{�5���Lu_��˚�s�y v�]      *      x������ � �      -   5   x�3�4�44�4�t�/M�I����44 "NS 6�42�L�,�4�=... (�
�      0   2   x�3�tL����4426�0��9]sr�+SS!�0�9�D0
.���� �2     