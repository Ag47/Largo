<?php

namespace Api\Controllers;

use App\Message;
use App\Http\Requests;
use Illuminate\Http\Request;
use Api\Transformers\MessageTransformer;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\Validator;

class MessageController extends BaseController
{
    public function index()
    {
        return $this->collection(Message::all(), new MessageTransformer);
    }

    public function store(Request $request)
    {
        $rules = [
            'text'  => 'max:100',
            'video' => 'mimes:mp4,x-flv,x-mpegURL,MP2T,3gpp,quicktime,x-msvideo,x-ms-wmv,mkv,qt',
            'image' => 'mimes:jpeg,bmp,png'
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

        return Message::create($request->only(['name', 'age']));
    }

    public function sendMessage(Request $request)
    {
        $rules = [
            'chatroom_id' => 'required',
            'user_id' => 'required',
            'text'  => 'max:100',
            'video' => 'mimes:mp4,x-flv,x-mpegURL,MP2T,3gpp,quicktime,x-msvideo,x-ms-wmv,mkv,qt',
            'image' => 'mimes:jpeg,bmp,png'
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
        if($request->send_time){
            $validDate = $this->checkIsAValidDate($request->send_time);
            if(!$validDate){
                return response()->json([
                    'resultCode' => 10000
                    ,'message' => 'Please provide a valid date']
                    , 400);
            }
            if (new \DateTime() > new \DateTime($request->send_time)){
                return response()->json([
                    'resultCode' => 10000
                    ,'message' => 'Whoop!, I can\'t send message to past']
                    , 400);
            }
        }

        $message = new Message;
        $message->cid = $request->chatroom_id;
        $message->uid = $request->user_id;
        $message->text = $request->text?$request->text:null;
        $message->send_time = $request->send_time?$request->send_time:null;
        $image = Input::file('image');
        $video = Input::file('video');
        if($image){
            $filename = time() . '-' . 'image';
            $extension = $image->getClientOriginalExtension();
            $image->move('image/', $filename . '.' . $extension);
            $message->image = asset('image/'.$filename . '.' . $extension);
        }
        if($video){
            $filename = time() . '-' . 'video';
            $extension = $image->getClientOriginalExtension();
            $image->move('video/', $filename . '.' . $extension);
            $message->video = asset('video/'.$filename . '.' . $extension);
        }
        $message->save();

        return response()->json([
                'resultCode' => 0
                ,'message' => $message]
                , 200);
    }

    public function show($id)
    {
        return $this->item(Message::findOrFail($id), new MessageTransformer);
    }

    public function update(Request $request, $id)
    {
        $rules = [
            'text'  => 'max:100',
            'video' => 'mimes:mp4,x-flv,x-mpegURL,MP2T,3gpp,quicktime,x-msvideo,x-ms-wmv,mkv,qt',
            'image' => 'mimes:jpeg,bmp,png'
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

        $message = Message::findOrFail($id);
        $message->update($request->only(['text', 'image', 'video']));
        return $message;
    }

    public function destroy($id)
    {
        return Message::destroy($id);
    }

    protected function checkIsAValidDate($myDateString){
        return (bool)strtotime($myDateString);
    }
}
