CREATE PROCEDURE CREATE_PRODUCT(UUID, integer, character varying, UUID)
language 'plpgsql'
as $$

begin

insert into public.product(id, price, product_name, merchant_id) values($1, $2, $3, $4);

end;
$$;


CREATE PROCEDURE UPDATE_PRODUCT(
	IN new_price INTEGER,
	IN new_product_name CHARACTER VARYING,
    IN product_id UUID
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE product
    SET 
        price = COALESCE(new_price, price),
        product_name = COALESCE(new_product_name, product_name)
    WHERE id = product_id;
END;
$$;


CREATE PROCEDURE DELETE_PRODUCT(
    IN product_id UUID
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM product WHERE id = product_id;
END;
$$;