<?php

namespace Api\Controllers;

use App\Chatroom;
use App\User;
use App\Http\Requests;
use Illuminate\Http\Request;
use Api\Transformers\ChatroomTransformer;
use Api\Transformers\MessageTransformer;
use Illuminate\Support\Facades\Validator;

class ChatroomController extends BaseController
{
    public function index()
    {
        return $this->collection(Chatroom::all(), new ChatroomTransformer);
    }

    public function getMessage($chatroom_id)
    {
        $chatroom = Chatroom::findOrFail($chatroom_id);
        return response()->json([
            'resultCode' => 0
            ,'data' => $chatroom->messages]
            , 200);
    }

    public function store(Request $request)
    {
        $rules = [
            'name' => 'required|max:100'
        ];
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success'
            ,'data' => Chatroom::create($request->only(['name']))]
            , 200);
    }

    public function storeWithUser(Request $request, $user_id)
    {
        $rules = [
            'name' => 'required|max:100'
        ];
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        $chatroom = new Chatroom;
        $chatroom->name = $request->name;
        $chatroom->save();

        $user = User::findOrFail($user_id);
        $user->cid = $chatroom->id;
        $user->save();
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success'
            ,'chatroom' => $chatroom
            ,'user' => $user]
            , 200);
    }

    public function inviteUser(Request $request)
    {
        if(!is_array($request->user_id)){
            return response()->json([
            'resultCode' => 10000
            ,'message' => 'The user id array field is required.']
            , 200);
        }
        $rules = [
            'chatroom_id' => 'required'
        ];
        foreach($request->user_id as $key => $val){
            $rules['user_id.'.$key] = 'required';
        }
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        $chatroom_id = $request->chatroom_id;
        foreach ($request->user_id as $u) {
            $user = User::findOrFail($u);
            $user->cid = $chatroom_id;
            $user->save();
        }

        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success']
            , 200);
    }

    public function show($id)
    {
        return $this->item(Chatroom::findOrFail($id), new ChatroomTransformer);
    }

    public function update(Request $request, $id)
    {
        $rules = [
            'name' => 'required|max:100'
        ];
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        $chatroom = Chatroom::findOrFail($id);
        $chatroom->update($request->only(['name']));
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success'
            ,'data' => $chatroom]
            , 200);
    }

    public function destroy($id)
    {
        Chatroom::destroy($id);
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success']
            , 200);
    }
}
