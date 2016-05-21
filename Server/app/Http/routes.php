<?php
$api = app('Dingo\Api\Routing\Router');

// Version 1 of our API
$api->version('v1', function ($api) {

	// Set our namespace for the underlying routes
	$api->group(['namespace' => 'Api\Controllers', 'middleware' => '\Barryvdh\Cors\HandleCors::class'], function ($api) {
		$api->get('users', 'UserController@index');
		$api->post('users', 'UserController@store');
		$api->get('users/{id}', 'UserController@show');
		$api->delete('users/{id}', 'UserController@destroy');
		$api->post('users/{id}/update', 'UserController@update');

		$api->get('chatroom', 'ChatroomController@index');
		$api->get('chatroom/getMessage/{chatroom_id}', 'ChatroomController@getMessage');
		$api->post('chatroom', 'ChatroomController@store');
		$api->post('chatroom/{user_id}', 'ChatroomController@storeWithUser');
		$api->post('chatroom/user/invite', 'ChatroomController@inviteUser');
		$api->get('chatroom/{id}', 'ChatroomController@show');
		$api->delete('chatroom/{id}', 'ChatroomController@destroy');
		$api->post('chatroom/{id}/update', 'ChatroomController@update');

		$api->get('messages', 'MessageController@index');
		$api->post('messages', 'MessageController@store');
		$api->post('sendMessage', 'MessageController@sendMessage');
		$api->get('messages/{id}', 'MessageController@show');
		$api->delete('messages/{id}', 'MessageController@destroy');
		$api->post('messages/{id}/update', 'MessageController@update');
	});

});
